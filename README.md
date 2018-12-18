# spring-boot-rsvp

Assumptions
1.	Pre specified listing of cities,date and time
2.	No usage for date of interest and city captured in registrant listing
3.	No specified date/time format
Known issues/Bugs
1.	In-memory H2 DB used instead of MySQL for portability reasons
2.	Language support missing for French/English
3.	Re-Selection of City/Date/Time again on same screen creates new select box
4.	Time slot id entry for registrant is not made for RSVP booking
5.	Missing foreign key constraints in DB
Tech Stack
Spring Boot 2.1, Apache POI , Javascript/jQuery , Spring data JPA , H2 DB
Approach used/ Reasons for provided solution
1.	Spring boot is used to quick start application development using spring along with in memory DB H2 for portability purpose
2.	On starting application,registrant list excel is read from the application classpath and saved in DB
3.	SQL file is used by spring data jpa to prepopulate the DB with listing of cities,dates and times
4.	User enters email id,which is validated against DB,if exists and rsvp not done,flow is navigated to RSVP screen
5.	User selects a city from this listing,respective date and respective time slot and makes the booking
6.	On form submission,particular  time slot is checked for availability once,to avoid any booking in meantime by some other user,if  available,slot is booked  and user rsvp is confirmed and any further user booking is disabled.
7.	In order to view H2 console,we can use http://localhost:8080/h2-console/ with                            JDBC URL as jdbc:h2:mem:testdb







Personal Work Notes
1.	Create a spring boot application
2.	Library needed for reading excel
3.	Date,city and time slots to be managed in real time
4.	Screen with their details and listing of selections for event
5.	Modules to be used:H2,Spring data JPA,Apache POI,Jstl,

Steps to be taken
1.	Read excel ,store in DB(service 1)
2.	Screen to enter email id;verify and move to screen 2 (service 2)
3.	Fetch all valid city listing(service 3)
4.	On selection,get valid dates(service 4)
5.	On selection,get valid time slots(service 5)
6.	Final RSPV confirmation – Update time to inactive
7.	hit service 5,no match,update date to inactive
8.	hit service 4,no match,update city to inactive
