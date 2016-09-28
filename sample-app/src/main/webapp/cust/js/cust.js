'use strict';

var cust = angular.module('cust', ['common']);

cust.controller('basicLoginController', ['$scope', '$location', '$http', 'AuthenticationService',
    function($scope, $location, $http, authenticationService) {
    this.username='';
    this.password='';
    this.login = function() {
    	$scope.dataLoading = true;
        authenticationService.ClearCredentials();
        $http.post('../api/' + this.username + '/login', {password: this.password})
        .success(function(response) {
            if(response.success) {
                authenticationService.SetCredentials($scope.username, $scope.password);
                $location.path('/');
            } else {
                $scope.error = response.message;
                $scope.dataLoading = false;
            }
        });
    };
}]);