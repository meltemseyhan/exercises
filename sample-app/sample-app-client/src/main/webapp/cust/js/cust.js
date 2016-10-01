'use strict';

var cust = angular.module('cust', ['common']);

cust.constant('url', '${cust.api.url}/');

cust.controller('basicLoginController', ['$scope', '$location', '$http', 'AuthenticationService', 'url',
    function($scope, $location, $http, authenticationService, url) {
    this.username='';
    this.password='';
    this.login = function() {
    	$scope.dataLoading = true;
        authenticationService.ClearCredentials();
        $http.post(url + this.username + '/login', {password: this.password})
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