# Question-4  Script Postgres   

Reading the "Challenge 1- Week 1 -- Question 4." document,  
I understood this script to code:

- Create two tables(Person and Address) with
particular fields
- Insert into the tables, and the Person register  
must be associated with an Address
- Select some fields in a join  

I have writed this script and run it in the SqlShell.  
After logged in the DBMS, I executed this command on  
propmt:  

\i 'path/question-4/script.sql'  

Then the process is:  

- CREATE a database named "question04"
- CONNECT to it
- CREATE the tables "Address" and "Person"
- INSERT into the tables "Address" and "Person"
- SELECT "Person.*" and "Address.*" JOINED
- DROP the tables "Address" and "Person"
- CONNECT to database "postgres"
- DROP database "question04"

*OBS: I have include the DROP TABLE and DATABASE  
because is possible to see the two tables content  
on the SELECT, so if you want persist the database  
and the tables just comment or delete the lines 53...58*