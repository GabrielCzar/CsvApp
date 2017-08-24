(function () {
    angular.module('CsvApp')
        .factory('Users', function ($http, API) {
            return {
                list : function () {
                    return $http.get(API + '/users');
                },
                create: function (user) {
                    return $http.post(API + '/users', user);
                }
            };
        });
})();