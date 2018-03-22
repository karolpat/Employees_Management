## Handbook *How to use the app*

[List of all active employees](http://localhost:5555/employees) - GET

*page* and *size* params would be usefull.


---
[List of all deleted employees](http://localhost:5555/employees/deleted) - GET

In fact, those employees are not deleted, but only hidden. They will not be shown on lists of employees, although they are still in the database ready to be restored.
Please, delete some employees first to check that.


---
[List of all employees](http://localhost:5555/employees/withDeleted) - GET

Shows list of all employees despite the fact they are deleted or not.


---
[Shows employees with name Jan](http://localhost:5555/employees/firstName/Jan) - GET

Shows all employees whose name is Jan. 
*http://localhost:5555/employees/firstName/{firstName}* - this is the raw path to insert demanded name into to the curly brackets.


---
[Shows employees with last name Kowalski](http://localhost:5555/employees/lastName/Kowalski) - GET

Shows all employees whose last name is Kowalski.
*http://localhost:5555/employees/lastName/{lastName}* - this is the raw path to insert demanded last name into to the curly brackets.


---
[Shows employee with email address karol@karol.pl](http://localhost:5555/employees/email/karol@karol.pl) - GET

Gives employee whose email address is karol@<span></span>karol.pl
*http://localhost:5555/employees/email/{email}* - this is the raw path to insert demanded email address into to the curly brackets.


---
[Deletes the employee of ID 5](http://localhost:5555/employees/delete/5) - PUT

Changes the active status of employee to false, so that employee is now like deleted but still appears in the database and could be easily restored with the following action.
*http://localhost:5555/employees/delete/{id}* - this is the raw path to insert demanded ID into to the curly brackets.

---
[Restores the deleted employee of ID 5](http://localhost:5555/employees/restore/5) - PUT

Changes the active status of employee to true, so that employee is again visible on the employees lists.
*http://localhost:5555/employees/restore/{id}* - this is the raw path to insert demanded ID into to the curly brackets.


---
[Adds new employee](http://localhost:5555/employees/add/Gal/Anonim/4/gal@anonim.pl) - POST

For this example I used attributes as follows:
* First name = Gal
* Last name = Anonim
* Position Id = 4
* Email = gal@anonim.pl
New employee with given attributes is being saved into the database. In case the given email is already present in the database or the position id does not exist, appropriate exception will be thrown - try to add two times employee with the same attributes.
*http://localhost:5555/employees/add/{firstName}/{lastName}/{position_ID}/{email}* - this is the raw path to insert demanded attributes into to the curly brackets.


---
[Shows list of all positions](http://localhost:5555/positions) - GET

Shows list of all positions in the company.


---
[Shows list of all positions with number of employees](http://localhost:5555/positions/count) - GET

Shows list of positions and number of employees on every consecutive position.


---
[Creates new position with name Killer](http://localhost:5555/positions/add/Killer) - POST

Creates new position that name is Killer.
*http://localhost:5555/positions/add/{name}* - this is the raw path to insert demanded position's name into to the curly brackets.

