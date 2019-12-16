/* Crea la estructura de la grilla. Requiere un tamaño, un elemento al cual será agregada y un ID para identificarla. */
const createGrid = function(size, element, id){

    let wrapper = document.createElement('DIV') // contenedor de la grilla
    wrapper.classList.add('grid-wrapper')
    
    // creamos las filas
    for(let i = 0; i < size; i++){
        let row = document.createElement('DIV')
        row.classList.add('grid-row')
        row.id =`${id}-grid-row${i}`
        wrapper.appendChild(row)// appends the row created in each itaration to the container

        // creamos la cantidad de celdas necesarias en cada fila
        for(let j = 0; j < size; j++){
            let cell = document.createElement('DIV')
            cell.classList.add('grid-cell')
            //if j and i are greater than 0, the drop event is activated
            if(i > 0 && j > 0){
                cell.id = `${id}${String.fromCharCode(i - 1 + 65)}${ j }`
                cell.dataset.y = String.fromCharCode(i - 1 + 65)
                cell.dataset.x = j
                cell.addEventListener('drop', function(event) {dropShip(event)})
                cell.addEventListener('dragover',function(event) {allowDrop(event)})      
            }
            // si j es igual a 0, se agregan las letras (es la primera columna)
            if(j===0 && i > 0){
                let textNode = document.createElement('SPAN')
                textNode.innerText = String.fromCharCode(i+64)
                cell.appendChild(textNode)
            }
            // si i es igual a 0, se agregan los números (es la primera fila)
            if(i === 0 && j > 0){
                let textNode = document.createElement('SPAN')
                textNode.innerText = j
                cell.appendChild(textNode)
            }
            row.appendChild(cell)
        }
    }

    element.appendChild(wrapper)

    // Evento para permitir soltar el barco.
    function allowDrop(ev) {
      ev.preventDefault();
    }

    // Evento para manejar cuando un barco es soltado
    function dropShip(ev) {
      ev.preventDefault();
      document.querySelector("#display p").innerText = ''
      // chequea si el elemento de destino es una celda
      if(!ev.target.classList.contains('grid-cell')){
        document.querySelector("#display p").innerText = 'movement not allowed'
        return
      }
      // variables del barco arrastrado
      let data = ev.dataTransfer.getData("ship");
      let ship = document.getElementById(data);
      // variables de la celda de destino
      let cell = ev.target
      let y = cell.dataset.y.charCodeAt() - 64
      let x = parseInt(cell.dataset.x)

      // Chequea si barco se excede de la grilla, en ese caso cancela el movimiento
      if(ship.dataset.orientation == 'horizontal'){
        if(parseInt(ship.dataset.length) + x > 11){
            document.querySelector("#display p").innerText = 'movement not allowed'
            return
        }
        for(let i = 1; i < ship.dataset.length;i++){
            let id = (cell.id).match(new RegExp(`[^${cell.dataset.y}|^${cell.dataset.x}]`, 'g')).join('')
            let cellId = `${id}${cell.dataset.y}${parseInt(cell.dataset.x) + i}`
            if(document.getElementById(cellId).className.search(/busy-cell/) != -1){
                document.querySelector("#display p").innerText = 'careful'
                return
            }
        }
      } else{
        if(parseInt(ship.dataset.length) + y > 11){
            document.querySelector("#display p").innerText = 'movement not allowed'
            return
        }

        for(let i = 1; i < ship.dataset.length;i++){
            let id = (cell.id).match(new RegExp(`[^${cell.dataset.y}|^${cell.dataset.x}]`, 'g')).join('')
            let cellId = `${id}${String.fromCharCode(cell.dataset.y.charCodeAt() + i)}${cell.dataset.x}`
            if(document.getElementById(cellId).className.search(/busy-cell/) != -1){
                document.querySelector("#display p").innerText = 'careful'
                return
            }
        }
      }
      // El barco toma la posición de la celda de destino
      ship.dataset.y = String.fromCharCode(y + 64)
      ship.dataset.x = x
      // el barco es agregado a la celda
      ev.target.appendChild(ship);

      checkBusyCells(ship, ev.target)
      
    }

}


function checkBusyCells(ship, cell){

    let id = (cell.id).match(new RegExp(`[^${cell.dataset.y}|^${cell.dataset.x}]`, 'g')).join('')
    let y = cell.dataset.y.charCodeAt() - 64
    let x = parseInt(cell.dataset.x)

    document.querySelectorAll(`.${ship.id}-busy-cell`).forEach(cell => {
        cell.classList.remove(`${ship.id}-busy-cell`)
        cell.classList.remove(`busy-cell`)
    })
      

    for(let i = 0; i < ship.dataset.length; i++){
        if(ship.dataset.orientation == 'horizontal'){
            document.querySelector(`#${id}${String.fromCharCode(y + 64)}${x + i}`).classList.add(`${ship.id}-busy-cell`)
            document.querySelector(`#${id}${String.fromCharCode(y + 64)}${x + i}`).classList.add(`busy-cell`)
        }else{
            document.querySelector(`#${id}${String.fromCharCode(y + 64 + i)}${x}`).classList.add(`${ship.id}-busy-cell`)
            document.querySelector(`#${id}${String.fromCharCode(y + 64 + i)}${x}`).classList.add(`busy-cell`)
        }
    }
}
