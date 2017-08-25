angular.module('CsvApp')
    .factory('UsersFactory', function ($http){
        var url = 'http://localhost:8080/api/users';
        return {
            list : function (){
                return $http({
                    method: 'get',
                    dataType: 'JSONP',
                    url: url
                })
            },
            create : function (user) {
                return $http.post(url, user);
            }
        };
    });