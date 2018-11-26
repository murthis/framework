$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("sample.feature");
formatter.feature({
  "line": 1,
  "name": "Launch Home page",
  "description": "",
  "id": "launch-home-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 17482466122,
  "status": "passed"
});
formatter.scenario({
  "line": 2,
  "name": "Launch Home page",
  "description": "",
  "id": "launch-home-page;launch-home-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "I Launch home page",
  "keyword": "When "
});
formatter.step({
  "line": 4,
  "name": "I should see home page logo",
  "keyword": "Then "
});
formatter.match({
  "location": "Home.launchHomePage()"
});
formatter.result({
  "duration": 3623656215,
  "status": "passed"
});
formatter.match({
  "location": "Home.seeHomePageLogo()"
});
formatter.result({
  "duration": 417862736,
  "status": "passed"
});
formatter.after({
  "duration": 1774007116,
  "status": "passed"
});
});