//создаем новое приложение app без зависимостей с контроллером indexController, реализацией которого будет функция
//function у которой есть доступ к глобальному хранилищу scope и модулю отправки http запросов
angular.module('app',[]).controller('indexController', function ($scope, $http) {
    //создаем константу пути, что-бы каждый раз ее использовать
    const contextPath = 'http://localhost:8189/market';

    //создаем функцию fillTable
    $scope.fillTableEasy = function () {
      //с помощью модуля http посылаем get запрос по адресу..
        $http.get(contextPath + '/products')
        //и когда нам придет ответ то сработает функция в тело которой попадет response
            .then(function (response) {
                //закидываем ответ в консоль браузера
                console.log(response);
                //вытаскиваем из ответа то что нам пришло (data) и кладем в переменную ProductsList
                $scope.ProductsList = response.data;
            });
    };

    //создаем функцию fillTable
    $scope.fillTable = function () {
        //посылаем параметризированный http запрос
        $http({
            //путь куда посылаем
            url: contextPath + '/products',
            //метод - get
            method: 'GET',
            //указываем request параметры
            params: {
                //если в поле filter существует, то в requestParam я положу его поле min_price, если нет то null
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        })
            //посылаем запрос и получаем некий ответ response
            .then(function (response) {
                //вытаскиваем из ответа то что нам пришло (data) и кладем в переменную ProductsList
                $scope.ProductsList = response.data;
            });
    };

    //создаем функцию submitCreateNewProduct
    $scope.submitCreateNewProduct = function () {
        //с помощью модуля http отправляем post запрос по адресу и перечисляем что туда положить (из scope достаем newProduct)
        $http.post(contextPath + '/products', $scope.newProduct)
            //когда ответ с бэкенда вернется выполняем функцию с параметрами вернувшегося ответа
            .then(function (response) {
                //отпечатываем в консоль браузера новый продукт
                console.log($scope.newProduct);
                //очищаем формы
                $scope.newProduct = null;
                //вызываем fillTable и перерисовываем таблицу с товарами
                $scope.fillTable();
        });
    };

    //вызываем функцию fillTable на исполнение
    $scope.fillTable();
});