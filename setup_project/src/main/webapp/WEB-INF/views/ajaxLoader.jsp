<!--* @author AMIT KUMAR MEHER* @version 1.0* @since 24-08-2017* @description The Process Loader for Ajax Responses-->
<style>
.ajax_loader_con {
	display: none;
	z-index: 9999;
	position: fixed;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	background: rgba(156, 156, 156, .8)
}

.ajax_loader_con>p {
	position: relative;
	top: calc(50% - 30px);
	text-align: center;
	color: #ff0202;
	font-weight: 600;
	font-size: 20px
}

.ajax_loader_con>p span {
	font-size: 30px;
	-webkit-animation-name: blink;
	animation-name: blink;
	-webkit-animation-duration: 1.4s;
	animation-duration: 1.4s;
	-webkit-animation-iteration-count: infinite;
	animation-iteration-count: infinite;
	-webkit-animation-fill-mode: both;
	animation-fill-mode: both
}

.ajax_loader_con>p span:nth-child(2) {
	-webkit-animation-delay: .3s;
	animation-delay: .3s
}

.ajax_loader_con>p span:nth-child(3) {
	-webkit-animation-delay: .5s;
	animation-delay: .5s
}

@
-webkit-keyframes blink { 0%{
	opacity: .1
}

20
%
{
opacity
:
1
}
100
%
{
opacity
:
.1
}
}
@
keyframes blink { 0%{
	opacity: .1
}

20
%
{
opacity
:
1
}
100
%
{
opacity
:
.1
}
}
.ajax-loader {
	position: relative;
	width: 60px;
	height: 60px;
	margin: 0 auto;
	top: calc(50% - 80px);
	-webkit-transform-origin: 50%, 50%;
	transform-origin: 50%, 50%;
	-webkit-animation: ajaxLoaderSpin 1.4s linear infinite;
	animation: ajaxLoaderSpin 1.4s linear infinite
}

.ajax-loader-circle {
	position: relative;
	width: 60px;
	height: 60px
}

.ajax-loader-circle-svg {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	-webkit-transform-origin: 50%, 50%;
	transform-origin: 50%, 50%;
	-webkit-animation: ajaxLoaderDashSpin 1.4s ease-in-out infinite;
	animation: ajaxLoaderDashSpin 1.4s ease-in-out infinite
}

circle {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	-webkit-animation: ajaxLoaderColors 5.6s ease-in-out infinite,
		ajaxLoaderDash 1.4s ease-in-out infinite;
	animation: ajaxLoaderColors 5.6s ease-in-out infinite, ajaxLoaderDash
		1.4s ease-in-out infinite;
	stroke-dasharray: 1570;
	stroke-width: 25;
	fill: none
}

@
-webkit-keyframes ajaxLoaderSpin { 0%{
	-webkit-transform: rotate(0);
	transform: rotate(0)
}

100
%
{
-webkit-transform
:
rotate(
270deg
);
transform
:
rotate(
270deg
)
}
}
@
keyframes ajaxLoaderSpin { 0%{
	-webkit-transform: rotate(0);
	transform: rotate(0)
}

100
%
{
-webkit-transform
:
rotate(
270deg
);
transform
:
rotate(
270deg
)
}
}
@
-webkit-keyframes ajaxLoaderColors { 0%{
	stroke: #000
}

25
%
{
stroke
:
#111
}
50
%
{
stroke
:
#222
}
75
%
{
stroke
:
#333
}
100
%
{
stroke
:
#444
}
}
@
keyframes ajaxLoaderColors { 0%{
	stroke: #000
}

25
%
{
stroke
:
#111
}
50
%
{
stroke
:
#222
}
75
%
{
stroke
:
#333
}
100
%
{
stroke
:
#444
}
}
@
-webkit-keyframes ajaxLoaderDash { 0%{
	stroke-dashoffset: 1413
}

50
%
{
stroke-dashoffset
:
392.5
}
100
%
{
stroke-dashoffset
:
1413
}
}
@
keyframes ajaxLoaderDash { 0%{
	stroke-dashoffset: 1413
}

50
%
{
stroke-dashoffset
:
392.5
}
100
%
{
stroke-dashoffset
:
1413
}
}
@
-webkit-keyframes ajaxLoaderDashSpin { 0%{
	-webkit-transform: rotate(0);
	transform: rotate(0)
}

50
%
{
-webkit-transform
:
rotate(
135deg
);
transform
:
rotate(
135deg
)
}
100
%
{
-webkit-transform
:
rotate(
450deg
);
transform
:
rotate(
450deg
)
}
}
@
keyframes ajaxLoaderDashSpin { 0%{
	-webkit-transform: rotate(0);
	transform: rotate(0)
}
50
%
{
-webkit-transform
:
rotate(
135deg
);
transform
:
rotate(
135deg
)
}
100
%
{
-webkit-transform
:
rotate(
450deg
);
transform
:
rotate(
450deg
)
}
}
</style>

<div class="ajax_loader_con">
	<div class="ajax-loader">
		<div class="ajax-loader-circle">
			<svg class="ajax-loader-circle-svg" viewBox="0 0 500 500">
				<circle cx="250" cy="250" r="239" /> </svg>
		</div>
	</div>
	<p>
		Please wait &nbsp; <span>.</span><span>.</span><span>.</span>
	</p>
</div>

<script>
  function showAjaxLoader() {
    $(".ajax_loader_con").show();
  }
  function hideAjaxLoader() {
    $(".ajax_loader_con").hide();
  }
</script>
