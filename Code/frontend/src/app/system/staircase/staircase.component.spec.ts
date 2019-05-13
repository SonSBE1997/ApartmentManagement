import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaircaseComponent } from './staircase.component';

describe('StaircaseComponent', () => {
  let component: StaircaseComponent;
  let fixture: ComponentFixture<StaircaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaircaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaircaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
