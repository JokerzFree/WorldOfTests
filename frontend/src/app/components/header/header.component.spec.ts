import {TestBed} from '@angular/core/testing';
import {HeaderComponent} from './header.component';

describe('Header', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({declarations: [HeaderComponent]});
    });
    it('should work', () => {
        let fixture = TestBed.createComponent(HeaderComponent);
        expect(fixture.componentInstance instanceof HeaderComponent).toBe(true, 'should create HeaderComponent');
    });
});
