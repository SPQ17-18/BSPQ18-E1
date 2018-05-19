BSPQ18-E1 project

Team members: Unai Bermejo, Ander Arguiñano, Iñigo García, Wolfgang Fischer

#############################################################################

Instructions

Order of the commands to execute the server and client

1) Create the DB with the sql file in the "db" folder
2) To compile : mvn clean compile
3) To create the structure : mvn datanucleus:schema-create
4) To load the DB : mvn exec:java -Pload
   To execute the client : mvn exec:java -Pclient
   To execute the server : mvn exec:java -Pserver
5) To test: mvn test
		This command will generate the Contiperf report
6) To generate Cobertura report: mvn cobertura:cobertura
		To consult the percentage tested: mvn cobertura:check
7) To generate Doxygen documentation: mvn doxygen:report

Github Page: https://spq17-18.github.io/BSPQ18-E1/
