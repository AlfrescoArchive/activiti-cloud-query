CHART_REPO := $(or $(CHART_REPOSITORY),http://jenkins-x-chartmuseum:8080)
CURRENT=$(shell pwd)
NAME := $(shell basename $(CURRENT))
ORG := $(or $(ORG),$(shell git remote -v | grep "(fetch)" | sed 's/.*\/\([^ ]*\)\/.*/\1/'))
OS := $(shell uname)
APP_NAME := $(or $(APP_NAME),$(NAME))
RELEASE_VERSION := $(or $(VERSION),$(shell cat ../../VERSION),$(shell sed -n 's/^version: //p' Chart.yaml))
RELEASE_ARTIFACT := $(or $(RELEASE_ARTIFACT),$(NAME))
RELASE_NAME := $(or $(RELEASE_NAME),$(NAME))

GITHUB_CHARTS_REPO := $(or $(GITHUB_CHARTS_REPO),$(shell git config --get remote.origin.url))
GITHUB_CHARTS_BRANCH := $(or $(GITHUB_CHARTS_BRANCH),gh-pages)

build: clean
	helm init --client-only
	helm repo add charts_activiti https://activiti.github.io/activiti-cloud-charts/
	helm repo add activiti-cloud-helm-charts https://activiti.github.io/activiti-cloud-helm-charts/
	helm dependency build
	helm lint
	helm install . --name ${RELASE_NAME} --debug --dry-run

install:  build
	helm install . --name ${RELASE_NAME}

upgrade: clean build
	helm upgrade ${RELASE_NAME} .

delete:
	helm delete --purge ${RELASE_NAME}

clean:
	rm -rf requirements.lock
	rm -rf charts
	rm -rf ${NAME}*.tgz

release: package
	curl  -u $(CHARTMUSEUM_CREDS_USR):$(CHARTMUSEUM_CREDS_PSW) --data-binary "@$(NAME)-$(RELEASE_VERSION).tgz" $(CHART_REPO)/api/charts
	rm -rf ${NAME}*.tgz%

$(NAME)-$(RELEASE_VERSION).tgz: build
	helm package .

package: $(NAME)-$(RELEASE_VERSION).tgz

preview: version release

github: package
	$(eval GITHUB_CHARTS_DIR := $(shell basename $(GITHUB_CHARTS_REPO) .git))
	git clone -b "$(GITHUB_CHARTS_BRANCH)" "$(GITHUB_CHARTS_REPO)" $(GITHUB_CHARTS_DIR)
	cp "$(NAME)-$(RELEASE_VERSION).tgz" $(GITHUB_CHARTS_DIR)
	cd $(GITHUB_CHARTS_DIR) && \
	   helm repo index . && \
	   git add . && \
	   git status && \
	   git commit -m "fix:(version) release $(NAME)-$(RELEASE_VERSION).tgz" && \
	   git pull && \
	   git push origin "$(GITHUB_CHARTS_BRANCH)" 
	rm -rf $(GITHUB_CHARTS_DIR)

version:
	sed -i -e "s/version:.*/version: $(RELEASE_VERSION)/" Chart.yaml
	sed -i -e "s|repository: .*|repository: $(DOCKER_REGISTRY)/$(ORG)/$(APP_NAME)|" values.yaml
	sed -i -e "s/tag: .*/tag: $(RELEASE_VERSION)/" values.yaml

tag: version
	git add --all
	git commit -m "release $(RELEASE_VERSION)" --allow-empty # if first release then no verion update is performed
	git tag -fa v$(RELEASE_VERSION) -m "Release version $(RELEASE_VERSION)"
	git push origin v$(RELEASE_VERSION)

updatebot/push-version: 
	@echo Resolving push versions for artifacts........
	$(eval ACTIVITI_CLOUD_NOTIFICATIONS_SERVICE_VERSION=$(shell mvn help:evaluate -Dexpression=activiti-cloud-notifications-service-graphql.version -q -DforceStdout -f ../../pom.xml))
	
	@echo Doing updatebot push-version.....
	for number in 1 2; do \
		updatebot -d ../../ push-version --kind maven org.activiti.cloud.examples:activiti-cloud-notifications-graphql $(RELEASE_VERSION) org.activiti.cloud.notifications.graphql:activiti-cloud-notifications-graphql-dependencies $(ACTIVITI_CLOUD_NOTIFICATIONS_SERVICE_VERSION); \
		rm -rf ../../.updatebot-repos/; \
		sleep $$[ ( $$RANDOM % 10 )  + 1 ]s; \
	done
