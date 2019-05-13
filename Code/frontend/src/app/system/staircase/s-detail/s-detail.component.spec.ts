import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SDetailComponent } from './s-detail.component';

describe('SDetailComponent', () => {
  let component: SDetailComponent;
  let fixture: ComponentFixture<SDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
