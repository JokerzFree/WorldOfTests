import {TestBed} from '@angular/core/testing';
import {FooterComponent} from './footer.component';

describe('Footer', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({declarations: [FooterComponent]});
    });
    it('should work', () => {
        let fixture = TestBed.createComponent(FooterComponent);
        expect(fixture.componentInstance instanceof FooterComponent).toBe(true, 'should create FooterComponent');
    });
});
