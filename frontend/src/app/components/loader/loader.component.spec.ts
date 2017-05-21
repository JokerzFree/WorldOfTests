import {TestBed} from '@angular/core/testing';
import {LoaderComponent} from './loader.component';

describe('loader', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({declarations: [LoaderComponent]});
    });
    it('should work', () => {
        let fixture = TestBed.createComponent(LoaderComponent);
        expect(fixture.componentInstance instanceof LoaderComponent).toBe(true, 'should create LoaderComponent');
    });
});
