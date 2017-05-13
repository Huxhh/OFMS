app.factory('request', ['$http', function ($http){
	var r = {};
	var count = 0;
	//弹窗
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
	//get请求
	r.get = function (url, callback1, callback2) {
		$http({
			url: url,
			method: 'GET'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' get failed');
		})
	}
	//post请求
	r.post = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'POST'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' post ailed');
		})
	}
	//put请求
	r.put = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'PUT'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' put ailed');
		})
	}
	//delete请求
	r.delete = function (url, data, callback1, callback2) {
		$http({
			url: url,
			data: data,
			method: 'delete'
		}).then(function (res) {
			callback1(res.data)
		}, function () {
			if (callback2) {
				callback2();
			}
			r.pop_up(url + ' delete ailed');
		})
	}
	// r.make_page()
	return r;
}]);