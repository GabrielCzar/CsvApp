(function(){
    angular
        .module('CsvApp')
        .controller('UsersController', function($scope, UsersFactory, $state, $http) {

            $scope.token = "";

            $scope.title = "Add User";

            $scope.users = [];

            $scope.newUser = {};

            $scope.loadUsers = function () {
                UsersFactory.list().then(function (result) {
                    angular.forEach(result.data, function (user) {
                        $scope.users.push(user);
                    });
                });
            };

            $scope.resetForm = function () {
                this.form.$setPristine();
                this.form.$setUntouched();
                $scope.newUser = {};
            };

            $scope.createUser = function () {
                this.form.$setDirty;

                if (this.form.$invalid) return;

                UsersFactory.create($scope.newUser).then($scope.clearUsers());

            };

            $scope.clearUsers = function () {
                $scope.users.push($scope.newUser);
                $scope.resetForm();
                $state.reload;
            }

            if ($scope.token === "") {
                UsersFactory.login().then(function(res) {
                    $scope.token = res.headers('Authorization');
                    $http.defaults.headers.common.Authorization = $scope.token;
                    $scope.loadUsers();
                });
            } else {
                $scope.loadUsers();
            }

        });
})();