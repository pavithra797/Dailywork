// const users = [
//     { name: "A", role: "admin"},
//     { name: "B", role: "user"},
//     { name: "C", role: "admin"},

// ];

// const freq = users.reduceRight((result,user)=>{
//     result[user.role] = (result[user.role] || 0) +1;
//     return result;
// },{});

// console.log(freq);




// const users = [
//     {name:"B", age:20},
//     {name:"A", age:20},
//     {name:"C", age:18},
// ];

// users.sort((a, b) => {
//     if (a.age !== b.age) return a.age - b.age;
//     return a.name.localeCompare(b.name);
// });

// console.log(users);


const users = [
    { name: "A", role: "admin", salary: 4000 },
    { name: "B", role: "user", salary: 4000 },
    { name: "C", role: "user", salary: 5000 },
    { name: "D", role: "admin", salary: 5050}

];

function groupBySalary(users) {
    const grouped = users.reduce((acc, user) => {
        if (!acc[user.salary]) acc[user.salary] = [];
        acc[user.salary].push(user);
        return acc;
    }, {});

    console.log(grouped);
}
groupBySalary(users);

function groupByRole(users) {
    const grouped = users.reduce((acc, user) => {
        if (!acc[user.role]) acc[user.role] = [];
        acc[user.role].push(user);
        return acc;
    }, {});
    console.log(grouped);
}
groupByRole(users);