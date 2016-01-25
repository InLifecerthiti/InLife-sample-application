# InLife-sample-application
This is a sample application, which performs how to integrate a new AAL service into the IN LIFE platform. The sample application is based on the lighting example of the universAAL platform and translates a web page to a preferred language. More specifically, the application has a client UI which performs the execution of the service. The user enters a url and a preferred language, he selects the corresponding option of calling the trasnlator service. The result of the execution is presented in the eclipse console.

Instructions for developers who want to build the code of the sample application themselves or just inspect it, but also for developers willing to implement their own applications over the INLIFE platform:

1) Install Java JDK

Download Java JDK (not JRE) from http://www.oracle.com/technetwork/es/java/javase/downloads/index.html

Versions: Currently Java 8 is not supported. The middleware is compatible with Java 1.5 and above, but different managers may require higher versions.

2) Install Maven

If Maven is not included in your IDE, you can install it standalone from http://maven.apache.org/download.cgi
Once installed edit your settings.xml (typically in folder .m2 in your user folder) and add uAAL repositories to the <profile><repositories> section:
http://depot.universaal.org/maven-repo/releases/
http://depot.universaal.org/maven-repo/snapshots/
http://depot.universaal.org/maven-repo/thirdparty/
http://depot.universaal.org/maven-repo/thirdparty-snapshots/
You can find an example file in https://github.com/universAAL/maven/tree/master/maven-repo-settings/settings.xml.

3) Install Eclipse IDE

Any other Java IDE can be used as long as you can create OSGi bundles and handle Maven projects with it. UniversAAL recommends Eclipse IDE, and the instructions here refer to it. Download it from https://eclipse.org/downloads/
If you plan to use the modeling and conversion tools from AAL Studio, you will need specifically Eclipse version 3.7.2.
Install Eclipse Plugins (optional)
For building Maven projects, Maven integration in Eclipse is helpful. Latest versions of Eclipse usually already include this plugin. Plugin install site: http://download.eclipse.org/technology/m2e/releases .
The AAL Studio is a set of uAAL-specific plugins. Plugin install site: http://depot.universaal.org/eclipse-update/ If you want the modelling tools, remember they require Eclipse 3.7.2.

4) Download source code

Clone a repository or check it out from https://github.com/universAAL.

Each repository usually contains several projects. Regular uAAL projects are Maven projects. Import them into Eclipse with: File > Import > Maven > Existing Maven Projects.

5) Build the source code

To compile the uAAL Maven projects you have to do a Maven install.

If you have the Eclipse Maven plugin, you can: right-click the project > Run as > Maven Install.
If you installed standalone Maven, open a console command line, go to the folder that holds the POM.xml file of the project and type mvn install.

Also, instructions regarding the installations could be found at:

https://github.com/universAAL/platform/wiki/RD-Development-Environment




## Funding Acknowledgement

The research leading to these results has received funding from the EU Framework Programme for Research and Innovation Horizon 2020
under grant agreement No.643442 IN LIFE (INdependent LIving support Functions for the Elderly).
