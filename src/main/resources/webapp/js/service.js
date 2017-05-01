app.factory('request', ['$http', function ($http){
	var r = {};
	var count = 0;
	r.pop_up = function (message) {
		count ++;
		var htm = `<div class="popup">
					<div class="bg bg_pop"></div>
					<div>
						<span>` + count + `</span>
						<p>` + message + `</p>
						<input type="button" value="确定" />
					</div>
				</div>`
		var pop = angular.element(htm);
		angular.element(document.getElementsByTagName('body')).append(pop);
		pop.find('input').on('click', function () {
			count --;
			pop.remove('div.popup')
		})
	}
	r.get = function (url, callback) {
		$http({
			url: url,
			method: 'GET'
		}).then(function (res) {
			callback(res.data)
		}, function () {
			r.pop_up(url + ' get failed');
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
			r.pop_up(url + ' post ailed');
		})
	}
	return r;
}]);