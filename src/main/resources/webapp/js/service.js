app.factory('request', ['$http', function ($http){
	var r = {};
	function popup(value) {

	}
	r.get = function (url, callback) {
		$http({
			url: url,
			method: 'GET'
		}).then(function (res) {
			callback(res.data)
		}, function () {
			popup('failed');
		})
	}
	r.post = function (url, data, callback) {
		$http({
			url: url,
			data: data,
			method: 'POST'
		}).then(function (res) {
			callback(res.data)
		}, function () {
			popup('failed');
		})
	}
	return r;
}]);