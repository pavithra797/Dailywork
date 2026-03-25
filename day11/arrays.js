// function demo1(){
//     let numbers = [1,2,3,4,5];
//     numbers.map((value, index)=> {
//         console.log(value);

//     })
// }
// demo1();


// const callback = (value, index) => {
//     console.log(value,index);
//     return value + 1;
// }

// function mapDemo1() {
//     let numbers =[1,2,3,4,5];
//     const increment= numbers.map(callback);
//         console.log(increment);
//         console.log(numbers);

// }
// mapDemo1()


// function filterDemo1(){
//     let numbers =[1,2,3,4,5];
//     const incremented = numbers.filter((value)=> value % 2 == 0);
//     console.log(incremented);
//     console.log(numbers);
// }
// filterDemo1()

function printPyramid(rows) {
    for (let i = 4; i >= 1; i--) {
        let row = "";
        for (let j = 1; j <= i; j++) {
            row += "*";
        }
        console.log(row);
    }
}
printPyramid();
