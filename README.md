# criteria-based-jobSearch-services

Test Project to build restful service that will send you list of companies based upon job and criteria that u define in the requested URL

Example of URL

1. Search companies with job cleaning and no criteria

   URL - http://localhost:8080/search/job/cleaning

2. Search companies with job cleaning and postCode as 2000 and minHours as 2 and minRooms as 1

   URL - http://localhost:8080/search/job/cleaning?criterias={postCode=2000;minHours=2;minRooms=1}
   
3. Search companies with job plumbing and no criteria

   URL - http://localhost:8080/search/job/plumbing
   
4. Search companies with job plumbing and postCode as 2034 and 24HoursService as false and minHours as 0

   URL - http://localhost:8080/search/job/plumbing?criterias={postCode=2034;minHours=0;24HoursService=false}
