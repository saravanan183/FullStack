 var app = angular.module("marksApp", []);
 app.controller("marksController", ["$scope", "$http", "$log", "saveMarkSvc", function ($scope, $http, $log, saveMarkSvc) {
     $scope.submitMarks = function () {
         $scope.nameOut = "hello" + $scope.nameVal;
         $scope.mark1Out = $scope.mark1Val;
         $scope.mark2Out = $scope.mark2Val;
         $scope.mark3Out = $scope.mark3Val;
         saveMarkSvc.saveMarks($http, $log, $scope.nameVal, $scope.mark1Val, $scope.mark2Val, $scope.mark3Val);
     }
}]);

 app.service("saveMarkSvc", function () {
     this.saveMarks = function ($http, $log, name, mark1, mark2, mark3) {
         var req = {
             "Name": name,
             "Mark1": mark1,
             "Mark2": mark2,
             "Mark3": mark3
         };
         
         $http.get('http://localhost:3000/markSheet', req).then(
             function successCallback(response) {
                 $log.debug(response);
             },
             function errorCallback(response) {
                 $log.debug(response);
             }
         );
     }
 })