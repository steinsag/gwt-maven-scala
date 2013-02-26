Scala + GWT + Spring Security + Maven Example
=============================================

Overview
--------

A working example combining a multi-project Maven build together with a GWT client and a Scala server backend, secured via Spring Security.

This example is based on the GWT webapp created by the [Maven GWT Plugin archetype](http://mojo.codehaus.org/gwt-maven-plugin/user-guide/archetype.html).

I have a similar example using only [Java on server side](https://github.com/steinsag/gwt-maven-example), too.

The example compiles via Maven or in IntelliJ IDEA 12. In IntelliJ IDEA 12, you have to disable external compilation in your project settings, see [belonging bug report](http://youtrack.jetbrains.com/issue/SCL-5341).


Running via Maven in GWT Dev Mode
---------------------------------

In order to run the example via Maven in GWT Dev Mode, you need to do:

1. Start the web application in Tomcat 7 via Maven
2. Start GWT Dev Mode via Maven
3. Run the application in your browser

To accomplish the first point, issue the following Maven command on a shell:

    mvn clean install
    mvn tomcat7:run-war-only

Your application is now deployed at http://127.0.0.1:8082/.

Now, you need to start GWT Dev Mode. Open a second shell and execute:

    mvn gwt:run -pl web

On success, the GWT Dev Mode window opens. Click *Launch Default Browser* to open it in GWT Dev Mode.

You can now make changes to your client Java code. Changes become immediately available as soon as you reloaded the page in the browser.


Deploying on Jelastic
---------------------

[Jelastic](http://jelastic.com/) is a cloud infrastructure service similar to Heroku or Amazon EC2. I included the [Jelastic Maven plugin](http://jelastic.com/docs/maven-plugin-jelastic) so that the project can be deployed directly via command-line.

Before you can do that, you need to:

1. Create a new Jelastic Java environment (Tomcat 7 with Java 7).
2. Add a profile to your private Maven settings file ($HOME/.m2/settings.xml) with the following settings:

        <profile>
            <id>jelastic</id>
            <activation>
                <activeByDefault>false</activeByDefault>
            </activation>
            <properties>
                <jelastic.email>YOUR_JELASTIC_EMAIL_ADDRESS</jelastic.email>
                <jelastic.password>YOUR_JELASTIC_PASSWORD</jelastic.password>
                <jelastic.api>API_URL_OF_YOUR_JELASTIC_PROVIDER</jelastic.api>
                <jelastic.environment>ENVIRONMENT_ID</jelastic.environment>
            </properties>
            <pluginRepositories>
                <pluginRepository>
                    <id>sonatype-oss-public</id>
                    <url>https://oss.sonatype.org/content/groups/public</url>
                    <releases>
                        <enabled>true</enabled>
                    </releases>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
        </profile>

Of course, you have to replace the placeholders with the correct values for your Jelastic account and environment!

Afterwards, you can deploy and run this example application via:

    mvn jelastic:deploy -Pjelastic

Hint: Before trying upload via Maven, you probably should first try manual deployment via Jelastic web interface to ensure your environment is set up correctly. While deploying, make sure to use context /parent.


Feedback
--------

Feedback is very welcome! You can reach me via:

* Email - seb-DOT-kde-AT-hpfsc.de
* Web - [sebstein.hpfsc.de](http://sebstein.hpfsc.de/)
* Twitter - [@stein2](https://twitter.com/stein2)


License
-------

Public Domain
