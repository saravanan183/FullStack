var app = angular.module("marksApp", []);
app.controller("marksController", function ($scope) {
    $scope.mkmarks = []
    $scope.nameVal = "Enter Name"
    $scope.mark1Val = 0
    $scope.mark2Val = 0
    $scope.mark3Val = 0

    $scope.submitMarks = function () {
        var sdnt = {
            "name": $scope.nameVal,
            "m1": $scope.mark1Val,
            "m2": $scope.mark2Val,
            "m3": $scope.mark3Val,
            "avg": (($scope.mark1Val+$scope.mark2Val+$scope.mark3Val)/3),
        }
        $scope.mkmarks.push(sdnt);
        $scope.reset();
    }

    $scope.reset = function () {
        $scope.nameVal = "Enter Name";
        $scope.mark1Val = 0;
        $scope.mark2Val = 0;
        $scope.mark3Val = 0;
    }

});
