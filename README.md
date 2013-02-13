Scala + GWT + Maven Example
===========================

Overview
--------

A working example combining a multi-project maven build together with GWT and a Scala server backend.

This example is based on the GWT webapp created by the [Maven GWT Plugin archetype](http://mojo.codehaus.org/gwt-maven-plugin/user-guide/archetype.html).

I have a similar example using only [Java on server side](https://github.com/steinsag/gwt-maven-example), too.

Currently, the example only compiles via Maven. I was not able to get it running in Intellij IDEA 12.


Running via Maven in GWT Dev Mode
---------------------------------

In order to run the example via Maven in GWT Dev Mode, you need to do:

1. Start the web application in Tomcat 7 via Maven
2. Start GWT Dev Mode via Mave
3. Run the application in your browser

To accomplish the first point, issue the following Maven command on a shell:

    mvn clean install
    mvn tomcat7:run-war-only

Your application is now deployed at http://127.0.0.1:8082/parent/.

Now, you need to start GWT Dev Mode. Open a second shell and execute:

    mvn gwt:run -pl web

On success, the GWT Dev Mode window opens. Click *Launch Default Browser* to open it in GWT Dev Mode.

You can now make changes to your client Java code. Changes become immediately available as soon as you reloaded your page in the browser.


Feedback
--------

Feedback is very welcome! You can reach me via:

* Email - seb-DOT-kde-AT-hpfsc.de
* Web - [sebstein.hpfsc.de](http://sebstein.hpfsc.de/)
* Twitter - [@stein2](https://twitter.com/stein2)


License
-------

Public Domain
