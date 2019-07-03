if(!Detector.webgl) Detector.addGetWebGLMessage();
var SCREEN_HEIGHT = window.innerHeight,
    SCREEN_WIDTH = window.innerWidth;
var container, stats, camera, controls, scene, renderer, mesh, light;
init();
animate();

function init(){
    container = document.getElementById('container');

    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(70, window.innerWidth/window.innerHeight, 1, 1000);
    // camera.position.z = 400;
    camera.position.set(0, 0, 500);
    camera.lookAt(scene.position);
    scene.add(camera);

    light = new THREE.AmbientLight(0x0000ff);
    scene.add(light);


    //var geometry = new THREE.PlaneGeometry(20, 20);
    var geometry = new THREE.BoxGeometry(150, 150, 150);
    var material = new THREE.MeshLambertMaterial({color: 0xffffff, shading: THREE.FlatShading});
    mesh = new THREE.Mesh(geometry, material);
    scene.add(mesh);

    renderer = new THREE.WebGLRenderer({clearColor: 0x000000, clearAlpha: 1, antialias: false});
    renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    renderer.autoClear = false;
    renderer.render( scene, camera);

    container.appendChild(renderer.domElement);

    window.addEventListener('resize', onWindowResize, false)

}

function animate(){
    mesh.rotation.y -= 0.01;
    mesh.rotation.x -= 0.01;
    requestAnimationFrame(animate);
    renderer.render(scene, camera);
}

function onWindowResize(event){
    renderer.setSize(window.innerWidth, window.innerHeight);
    camera.aspect = window.innerWidth / window.innerHeight;
    // 重新产生矩阵
    camera.updateProjectionMatrix()
}