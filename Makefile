default: dep

dep:
	mvn install:install-file \
		-Dfile=./lib/MinimapAPI.jar \
		-DgroupId=me.liec0dez \
		-DartifactId=MinimapAPI \
		-Dversion=1.0 \
		-Dpackaging=jar \
		-DlocalRepositoryPath=./repo