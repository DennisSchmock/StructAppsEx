'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl',
                    controllerAs: 'v5'
                });
            }])

        .controller('View5Ctrl', function ($scope, $http) {
            $scope.member = null;
    
            $http.get('api/member')
                    .success(function (data, status, headers, config) {
                        $scope.user = data.user;
                    })
                    .error(function (data, status, headers, config) {

                    });


        });