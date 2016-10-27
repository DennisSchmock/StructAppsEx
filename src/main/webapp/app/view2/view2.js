'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl',
                    controllerAs: 'v2'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope) {
            $scope.teams = [];
            $http({
                method: 'GET',
                url: 'api/demouser'
            }).then(function successCallback(res) {
                $scope.teams = res.data;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });

        });