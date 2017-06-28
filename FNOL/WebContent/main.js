var app1 = angular.module("app1", ["ngRoute", "studentModule"]);

console.log('test');

app1.config(function($routeProvider){
   $routeProvider
       .when("/home", {templateUrl: "home.html"})
       .when("/viewStudents", {templateUrl: "viewStudents.html"})
       .when("/addStudents", {templateUrl: "addStudents.html"});
    
    
});