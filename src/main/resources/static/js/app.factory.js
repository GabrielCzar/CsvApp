angular.module('CsvApp')
    .factory('UsersFactory', function ($http, URLS, USER){
        return {
            login: function () {
                return $http.post('/login', USER);
            },
            list : function (){
                return $http({
                    method: 'get',
                    dataType: 'JSONP',
                    url: URLS.API
                })
            },
            create : function (user) {
                return $http.post(URLS.API, user);
            }
        };
    });