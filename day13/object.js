// const user = {name:"john",age:20};
// letJsonStr = JSON.stringify(user);

// let temp = JSON.parse(jsonStr);

// temp.isAdult = temp.age>=18;

// let finalObj = temp;
// console.log(finalObj);



// function diff(a,b){
//     const result = {};

//     for(let key in b){
//         if(a[key]!== b[key]){
//             result[key]={old:a[key],new:b[key]};
//         }
//     }
//     return result;
// }

// const a={name:"A", age:20};
// const b={name:"A", age:21};

// console.log(diff(a,b));


// function validate(obj ,requiredKeys){
//     return requiredKeys.every(key=> key in obj);
// }
// const data = {name:"john", age:25};
// console.log(validate(data,["name","age","email"]));

for (var i = 0; i < 3; i++) {
  setTimeout(() => console.log(i), 100);
}

