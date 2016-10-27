'use strict';

angular.module('myApp.view3', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view3', {
    templateUrl: 'app/view3/view3.html',
    controller: 'View3Ctrl',
    controllerAs: 'v3'
  });
}])

.controller('View3Ctrl', function($http,$scope) {
    $scope.users = [];
  $http.get('api/demoadmin')
            .success(function (data, status, headers, config) {
              $scope.users = data;
            })
            .error(function (data, status, headers, config) {
              
             });
 
});