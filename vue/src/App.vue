<template>
  <div id="app">
    <router-view />
    <foot />
  </div>
</template>

<script>
import Foot from '../src/components/Foot.vue';

export default {
  components: { Foot }
}
</script>


<style>
@import "https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css";


#app {
  /* display: flex;
  flex-direction: column;
  align-items: center; */
  font-family: 'Poppins','Roboto','Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #D6F8D6;  
  margin: 60px;
  width: 100%;
  position: relative;
  min-height: 100vh;
  
}

html {
  background-image: url("assets/dark-blue-background.jpeg");
  background-size: 90%;
} 

body { 
  display: flex;
  justify-content: center;
  text-align: center;
}

button{
	width: 50%;
	height: 40px;
	margin: 10px auto;
	justify-content: center;
	display: block;
	color: #fff;
	background: #573b8a;
	font-size: 1em;
	font-weight: bold;
	margin-top: 20px;
	outline: none;
	border: none;
	border-radius: 10px;
	transition: .2s ease-in;
	cursor: pointer;
}
button:hover{
	background: #6d44b8;
}


input {
	width: 50%;
  
}

#footer {
  position: fixed;
  bottom: 0;
  width: 100%;    
}
.genre {

  text-transform: capitalize;
;
}

</style>