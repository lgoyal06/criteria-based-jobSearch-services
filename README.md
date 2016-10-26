# criteria-based-jobSearch-services

Test Project to build restful service that will send you list of companies based upon job and criteria that u define in the requested URL

Example of URL

1. Search companies with job cleaning and minHours as 2 hours

   URL - http://localhost:8080/search/job/cleaning?criterias={minHours=2}

2. Search companies with job cleaning and no criteria

   URL - http://localhost:8080/search/job/cleaning

3. Search companies with job plumbing and postCode as 2000 and 24HourService as true

   URL - http://localhost:8080/search/job/plumbing?criterias={postCode=2000;24HourService=true}
   
4. Search companies with job plumbing and no criteria

   URL - http://localhost:8080/search/job/plumbing
