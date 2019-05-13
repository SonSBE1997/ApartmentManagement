import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WDetailComponent } from './w-detail.component';

describe('WDetailComponent', () => {
  let component: WDetailComponent;
  let fixture: ComponentFixture<WDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
