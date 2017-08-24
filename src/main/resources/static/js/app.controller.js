(function(){
    angular
        .module('CsvApp')
        .controller('CsvController', function ($scope, Users) {
            this.title = "Csv Users";
            this.users = [];
            this.newUser = {};

            var loadView = function () {
                Users.list().then(function (result) {
                    angular.forEach(result.data, function (user) {
                        this.users.push(user);
                    });
                });
            };

            this.createUser = function () {
                this.form.$setDirty;

                if (this.form.$invalid) return;

                Users.create(newUser).then(loadView());
                this.newUser = {};
            };

            loadView();
        });
})();