var cc = cc || {};
cc.registration = {

	/**
	 * If checkbox is checked given inputs will be disabled=true, otherwise disabled=false
	 * @param {jQuery} checkbox
	 * @param {jQuery} inputs
	 * @param {jQuery} disabledClassOnChecked
	 * @param {jQuery} hidenOnChecked
	 */
	swicher: function(checkbox, inputs, disabledClassOnChecked, hidenOnChecked) {

		function toggle() {
			var checked = checkbox.is(':checked');
			inputs.each(function(i) {
				$(this).attr('disabled', checked);
			});

			if (checked) {
				hidenOnChecked.css('visibility', 'hidden');
				disabledClassOnChecked.addClass('disabled');
			} else {
				hidenOnChecked.css('visibility', 'visible');
				disabledClassOnChecked.removeClass('disabled');
			}
		}

		checkbox.bind('change', function() {
			toggle();
		});
	}
};
