/**
 * 复选框全选/全取消之效果,#head_check是总开关复选框标签ID
 * 
 * @returns
 */
function headInfluence() {
	if ($('#head_check').prop('checked') == true) {
		$(':checkbox[class="td_order_number"]').prop('checked', true);
	} else {
		$(':checkbox[class="td_order_number"]').prop('checked', false);
	}

}

/**
 * tbl_head_check 表头复选框总开关类名
 * td_order_number 表格体每行复选框类名
 * 
 * @returns
 */
function boxsInfluence() {
	// .tbl_head_check为标题栏复选框总开关类路径
	var checked = $('.tbl_head_check').prop('checked');

	var td_order_number = $(':checkbox[class="td_order_number"]');

	if (checked == true) {
		td_order_number.prop('checked', true);
	} else {
		td_order_number.prop('checked', false);
	}

}