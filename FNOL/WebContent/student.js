var claimModule = angular.module("studentModule",[]);

claimModule.controller("addStudentController", function($scope, claimFactory, claimService){
    $scope.master = {};
    
    $scope.update = function(claim) {
        $scope.master = angular.copy(claim);
        claimFactory.update($scope.master);
    };
    
    $scope.reset = function(form) {
        
        $scope.claim = angular.copy($scope.master);
    }
    
    $scope.reset();
});

claimModule.controller("viewStudentController", function($scope, claimFactory, claimService){
    $scope.claims = claimFactory.list();
});

claimModule.factory("stuedntFactory", function(){
    

        var claimList = [];
    
        var thisService = {};
        
        thisService.update = function(claim){

            claimList.push(claim);
        } 
        
        thisService.list = function(){
            return claimList;
        }
        
        return thisService;

});

claimModule.service("studentService", function(){    

        var claimList = [];
        
        this.update = function(claim){

            claimList.push(claim);
        } 
        
        this.list = function(){
            return claimList;
        }

});
