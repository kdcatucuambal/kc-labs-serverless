build-LabsGreetingsGETHelloLambda:
	mvn clean package
	mvn dependency:copy-dependencies -DexcludeScope=provided
	cp -rf ./target/classes/* $(ARTIFACTS_DIR)/

build-LabsGreetingGETNameLambda:
	mvn clean package
	mvn dependency:copy-dependencies -DexcludeScope=provided
	cp -rf ./target/classes/* $(ARTIFACTS_DIR)/

build-LabsBalancesGETByIdLambda:
	mvn clean package
	mvn dependency:copy-dependencies -DexcludeScope=provided
	cp -rf ./target/classes/* $(ARTIFACTS_DIR)/