const { describe } = require('mocha');

const add = require('../app');
const { expect } = require('chai');

describe('testing maths operations', () => {
    beforeEach(() => {
        console.log('before each');
    })
    it('normal add', () => {
        const result = add(2, 3);
        expect(result).to.equal(5);
    })

    it('normal add with -ve numbers', () => {
        const result1 = add(-2, -3);
        expect(result1).to.equal(-5);
    })
})
