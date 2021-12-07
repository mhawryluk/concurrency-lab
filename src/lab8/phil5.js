var Fork = function() {
    this.state = 0;
    return this;
}

Fork.prototype.acquire = function(cb) { 
    // zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
    // (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
    // 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
    //    i ponawia probe itd.

    let self = this

    function wait (time){
        setTimeout(() => {
            if (self.state == 0){
                self.state = 1;
                if (cb) cb();
            } else {
                console.log(`waiting ${time} s`)
                wait(time*2)
            }
        }, time);
    }

    wait(1);
}

Fork.prototype.release = function() { 
    if (this.state == 0) {
        console.log('ALREADY RELEASED')
    }
    this.state = 0; 
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

Philosopher.prototype.eat = function(){
    console.log(`${this.id} eating...`);
    this.forks[this.f1].release();
    this.forks[this.f2].release();
}

Philosopher.prototype.startNaive = function(count) {
    let f1 = this.f1
    let f2 = this.f2
    
    // zaimplementuj rozwiazanie naiwne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
        
    setTimeout(
        () => this.forks[f1].acquire(() => {
            this.forks[f2].acquire(() => {
                this.eat();
                if (count > 1) this.startNaive(count-1);
            });
        }),
        0
    )
}

Philosopher.prototype.startAsym = function(count) {
    let f1, f2;


    if (this.id % 2 == 0){
        f1 = this.f1
        f2 = this.f2
    } else {
        f2 = this.f1
        f1 = this.f2
    }
    
    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    setTimeout(
        () => this.forks[f1].acquire(() => {
            this.forks[f2].acquire(() => {
                this.eat();
                if (count > 1) this.startAsym(count - 1);
            });
        }),
        0
    )
}

Philosopher.prototype.startConductor = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    
    // zaimplementuj rozwiazanie z kelnerem
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow
}


var N = 5;
var forks = [];
var philosophers = []
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

for (var i = 0; i < N; i++) {
    philosophers[i].startAsym(5);
}