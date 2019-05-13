import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ADetailComponent } from './a-detail.component';

describe('ADetailComponent', () => {
  let component: ADetailComponent;
  let fixture: ComponentFixture<ADetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ADetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ADetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
