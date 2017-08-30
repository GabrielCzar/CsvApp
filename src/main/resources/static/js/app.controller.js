(function(){
    angular
        .module('CsvApp')
        .controller('UsersController', function($scope, UsersFactory, $http, $sce) {
            $scope.title = "Add User";

            $scope.users = [];

            $scope.feedback = "";

            var feedbackHTML = function (message, cor) {
                return "<div id='feedback' class='chip " + cor + " lighten-2 alert'><h6 class='white-text center'>" + message + "</h6></div>";
            };

            var clearFeedback = function () {
                setTimeout(function () {
                    $scope.feedback = "";
                    var elmn = angular.element( document.querySelector( '#feedback' ) );
                    elmn.remove();
                }, 3000);
            };

            $scope.loadUsers = function () {
                UsersFactory.list().then(function (result) {
                    angular.forEach(result.data, function (user) {
                        $scope.users.push(user);
                    });
                });
            };

            $scope.createUser = function (user) {
                UsersFactory.create(user).then(function () {
                    $scope.feedback = $sce.trustAsHtml(feedbackHTML("Success! User inserted!", 'green'));
                    clearFeedback();
                    $scope.users.push(user);
                    delete user;
                }).catch(function () {
                    $scope.feedback =  $sce.trustAsHtml(feedbackHTML("Error! User don't inserted!", 'red'));
                    clearFeedback();
                });
            };

            if ($http.defaults.headers.common.Authorization === undefined) {
                UsersFactory.login().then(function(res) {
                    $http.defaults.headers.common.Authorization = res.headers('Authorization');
                    $scope.loadUsers();
                });
            } else {
                $scope.loadUsers();
            }

        });
})();