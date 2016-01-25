# InLife-sample-application
This is a sample application, which performs how to integrate a new AAL service into the IN LIFE platform. The sample application is based on the lighting example of the universAAL platform and translates a web page to a preferred language. More specifically, the application has a client UI which performs the execution of the service. The user enters a url and a preferred language, he selects the corresponding option of calling the translator service. The result of the execution is presented in the eclipse console.

Instructions for building the code of the sample application or integrate a new AAL service over the INLIFE platform:

1) Install Java JDK

Download Java JDK (not JRE) from http://www.oracle.com/technetwork/es/java/javase/downloads/index.html

Suggested version Java 7

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

Suggested Eclipse version 3.7.2.

Install Eclipse Plugins (optional)

For building Maven projects, Maven integration in Eclipse is helpful. Latest versions of Eclipse usually already include this plugin. Plugin install site: http://download.eclipse.org/technology/m2e/releases .

4) Download source code

Clone a repository or check it out.

Import them into Eclipse with: File > Import > Maven > Existing Maven Projects.

5) Build the source code

To compile the Maven projects you have to do a Maven install.

If you have the Eclipse Maven plugin, you can: right-click the project > Run as > Maven Install.
If you installed standalone Maven, open a console command line, go to the folder that holds the POM.xml file of the project and type mvn install.


Also, instructions regarding the installations could be found at:

https://github.com/universAAL/platform/wiki/RD-Development-Environment

Specific instructions for building and running the sample application:

1) start Eclipse with a new workspace

2) download and import the sample application as "Existing Maven Projects"

3) compile the project by right-clicking the project and selecting Run As -> 8 Maven Install (which will print a line saying "BUILD SUCCESS" in the console)

4) copy the configuration files; just copy the folder [rundir] (https://github.com/universAAL/distro.pax/tree/rundir) to the workspace directory, i.e. as c:\inlife\workspaces\sampleapplication\rundir.

5) open the Run Configurations manager. Select the Run Configuration named "Example-Lighting-LATEST_Complete_Original". When opening it you might see a dialog named "Resolve composites"; select "No".

6)click the "Run"-button in the lower right of the Run Configurations manager and wait for the sample application to start. 

## Funding Acknowledgement

The research leading to these results has received funding from the EU Framework Programme for Research and Innovation Horizon 2020
under grant agreement No.643442 IN LIFE (INdependent LIving support Functions for the Elderly).
