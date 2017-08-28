angular.module('CsvApp')
    .constant('URLS', {
        API : '/api/users',
        LOGIN : '/login'
    })
    .constant('USER', { username: 'admin', password: 'password' });