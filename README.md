# criteria-based-jobSearch-services

Test Project to build restful service that will send you list companies based upon job and rules that u define in the requested URL

Example of URL

1. To Get list of all companies with job cleaning and minHours as 2 hours 

URL - http://localhost:8080/search/job/cleaning?criterias={minHours=2}

2. To Get list of all companies with job cleaning and no criteria

URL - http://localhost:8080/search/job/cleaning

3. To Get list of all companies with job plumbing and with postCode as 2000 and 24HourService as true

URL - http://localhost:8080/search/job/plumbing?criterias={postCode=2000;24HourService=true}
