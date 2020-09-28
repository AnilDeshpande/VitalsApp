# VitalsApp
 Just Demo App

 The has been written using the MVVM architecture using Simple Java.
 Hilt has been used for Dependency Injection
 MainActivity hosts Two Fragments -
    - MainFragment shows just the first info with four buttons. OnLick of any button new Fragment is loaded
    - VitalsInfoFragment shows the chart

 The applications tries to load the Vitals data from API call
 If the API call is a success
    - The the response is cached in SharedPreference (I could I have used database but there is no id in response)
    - The it is rendered
    - Till it is rendered you will see progress bar
 If the API call fails
    - Then at first we try to fetch the value from SharedPreference where we had cached the value from previoys successfull attempt
    - If there is no content available then, then we fetch it from Mock data saved in asset

 UI testing has been done in Espresso

 Git Flow
 Branching/Pull Request/Merging as been done.
 Very minimum coding is done on master Branch.
 Always any development is in feature branch and then pulled in to master branch

 Potential Improvement
    - I could have used other featured of Hilt to demonstrate ActivityComponent/FragmentComponent, but I have kept is very minimum due to paucity of time
    - I did't get the time to complete the Unit Tests.
    - I have not used Android Navigation Component. Just to keep it simple. 
