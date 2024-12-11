# Incidement Management Project
### Description
This is a simple Spring Boot project that provide 4 APIs to manage incidents
1. Create: create a new incident. Throws a 409 conflict error if the incident already exist
2. Update: Update content of an existing incident. Throws a 404 error if not found.
3. Delete: Delete an incident. Throws a 404 error if not found.
4. List All: list all the current incident in string-ified format.

### Additional Dependency:
#### Redis: 
I chose Redis as the main storage, since it requires that we store data in memory. However, while Redis has good performance on 
single get request, scanning the whole table to support the "List All" functionality is quite expensive. 

I think there might be some other database that support this feature with better performance, But I am not too familiar with them.


### How to run
1. Download Redis
2. Start redis server by running ```redis-server```.
3. Run the IncidentManagement application.
4. For List All, , go to http://127.0.0.1:3000/all.
5. For other request, use a API platform, such as Postman, to submit request. Example
   To create a new Incident, POST request to URL http://localhost:8080/create, with following payload
    ```
   {
	"incidentId": "uniqueId1",
    "incidentTitle": "title 1",
    "description": "description 1"
    }
   ```

